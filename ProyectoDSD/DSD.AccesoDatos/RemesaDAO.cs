using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using NHibernate.Criterion;
using NHibernate;

public class RemesaDAO : BaseDAO<Remesa, int>
{
    public Remesa ObtenerByUsuario(string tipoDoc, string numDoc, int pin)
    {
        using (ISession sesion = NHibernateHelper.ObtenerSesion())
        {
            ICollection<Remesa> lista = sesion.QueryOver<Remesa>().Where(obj => tipoDoc == obj.TipoDoc &&
                numDoc == obj.NumDoc && obj.PIN == pin && obj.Estado != "Cobrada").List<Remesa>();

            if (lista.Count() == 0)
            {
                return null;
            }
            return lista.ToList<Remesa>()[0];
        }
    }
    
}

