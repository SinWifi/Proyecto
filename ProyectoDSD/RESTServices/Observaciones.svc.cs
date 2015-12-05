using System;
using System.Collections.Generic;
using System.Linq;
using System.Messaging;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RESTServices
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "Observaciones" en el código, en svc y en el archivo de configuración a la vez.
    // NOTA: para iniciar el Cliente de prueba WCF para probar este servicio, seleccione Observaciones.svc o Observaciones.svc.cs en el Explorador de soluciones e inicie la depuración.
    public class Observaciones : IObservaciones
    {
        private ObservacionDAO observacionDAO = null;
        private ObservacionDAO DAO
        {
            get
            {
                if (observacionDAO == null)
                    observacionDAO = new ObservacionDAO();
                return observacionDAO;
            }
        }

        public string CrearObservacion(Observacion observacion)
        {
            if (observacion.remesa.Estado.Equals("Cancelado"))
            {
                throw new WebFaultException<string>("No es posible el envío, remesa CANCELADA", System.Net.HttpStatusCode.InternalServerError);
            }
            else
            {
                try
                {
                    String rutaCola = @".\private$\observaciones";
                    if (!MessageQueue.Exists(rutaCola))
                        MessageQueue.Create(rutaCola);

                    MessageQueue cola = new MessageQueue(rutaCola);
                    Message mensaje = new Message();
                    mensaje.Label = "Observación" + observacion.remesa.Numero;
                    mensaje.Body = observacion;
                    cola.Send(mensaje);
                    return "Su observación ha sido enviada correctamente";
                }
                catch (Exception e)
                {
                    throw new WebFaultException<string>("No es posible el envío", System.Net.HttpStatusCode.InternalServerError);
                }
            }
        }

        public List<Observacion> ListarObservaciones()
        {            
            String rutaCola = @".\private$\observaciones";
            if (!MessageQueue.Exists(rutaCola))
                MessageQueue.Create(rutaCola);
            MessageQueue cola = new MessageQueue(rutaCola);
            cola.Formatter = new XmlMessageFormatter(new Type[] { typeof(Observacion) });
            int cantidad = cola.GetAllMessages().Count();
            for (int i = 0; i < cantidad; i++)
            {
                Message mensaje = cola.Receive();
                Observacion observacion = (Observacion)mensaje.Body;
                try 
                {
                    DAO.Crear(observacion); 
                }catch (Exception e) { }
            }

            return DAO.ListarTodos().ToList();
        }
    }
}
