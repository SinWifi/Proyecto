﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace SOAPServices
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "Service1" en el código, en svc y en el archivo de configuración.
    public class Remesas : IRemesas
    {
        private RemesaDAO remesaDAO = null;

        private RemesaDAO DAO
        {
            get
            {
                if (remesaDAO == null)
                    remesaDAO = new RemesaDAO();
                return remesaDAO;
            }
        }

        public Remesa CrearRemesa(int pin, DateTime fecha, Agencia agencia, Moneda moneda, decimal monto)
        {
            Remesa remesa = new Remesa()
            {
                PIN = pin,
                Fecha = fecha.ToString(),
                Agencia = agencia,
                Moneda = moneda,
                Monto = monto,
                Estado = "Pendiente"
            };
            return DAO.Crear(remesa);
        }

        public Remesa ObtenerRemesa(int numero)
        {
            validarHora();
            return DAO.Obtener(numero);
        }

        public Remesa ModificarRemesa(int numero, int pin, DateTime fecha, Agencia agencia, Moneda moneda, decimal monto, string estado)
        {
            Remesa remesa = new Remesa()
            {
                Numero = numero,
                PIN = pin,
                Fecha = fecha.ToString(),
                Agencia = agencia,
                Moneda = moneda,
                Monto = monto,
                Estado = estado
            };
            return DAO.Modificar(remesa);
        }

        public void EliminarRemesa(int numero)
        {
            Remesa remesa = DAO.Obtener(numero);
            DAO.Eliminar(remesa);
        }

        public List<Remesa> ListarRemesas()
        {
            return DAO.ListarTodos().ToList();
        }

        private void validarHora(){
            DateTime fechaActual = DateTime.Now;

            int horaActual = fechaActual.Hour;

            if (horaActual >= 0 && horaActual <= 2)
            {
                throw new FaultException<HoraExcedidaException>(new HoraExcedidaException("La informaci\u00f3n mostrada no puede ser actualizada"));
            }
        }

        public Remesa ObtenerRemesaByUsuario(string tipoDoc, string numDoc, int pin)
        {
            Remesa remesa = DAO.ObtenerByUsuario(tipoDoc, numDoc, pin);
            if (remesa == null)
            {
                return null;
            }

            validarHora();
            validarFechaLimite(remesa);

            return remesa;
        }

        public Remesa GenerarCobro(string tipoDoc, string numDoc, int pin)
        {
            Remesa remesa = DAO.ObtenerByUsuario(tipoDoc, numDoc, pin);
            if (remesa == null)
            {
                return null;
            }

            validarHora();
            validarFechaLimite(remesa);

            remesa.Estado = "Cobrada";
            remesa.Fecha = Convert.ToDateTime(remesa.Fecha).ToString("yyy-MM-dd");
            return DAO.Modificar(remesa);
        }

        private void validarFechaLimite(Remesa remesa)
        {
            DateTime fechaRemesa = Convert.ToDateTime(remesa.Fecha);
            DateTime fechaActual = DateTime.Now;

            TimeSpan ts = fechaActual - fechaRemesa;

            int diferenciaDias = ts.Days;

            if (diferenciaDias > 10)
            {
                throw new FaultException<FechaLimiteException>(new FechaLimiteException("Esta remesa ha sido extornada"));
            }
        }
    }
}
