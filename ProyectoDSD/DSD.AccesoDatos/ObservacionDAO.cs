using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;

namespace DSD.AccesoDatos
{
    class ObservacionDAO
    {
        public Observacion Crear(Observacion obsACrear)
        {
            Observacion obsCreada = null;
            string sql = "INSERT INTO Remesas VALUES(@id, @rem, @des,@fec)";
            using (SqlConnection con = new SqlConnection(ConexionUtil.Cadena))
            {
                con.Open();
                using (SqlCommand com = new SqlCommand(sql, con))
                {
                    com.Parameters.Add(new SqlParameter("@cod", obsACrear.ID));
                    com.Parameters.Add(new SqlParameter("@ben", obsACrear.remesa));
                    com.Parameters.Add(new SqlParameter("@cli", obsACrear.descripcion));
                    com.Parameters.Add(new SqlParameter("@cli", obsACrear.fecha));
                    com.ExecuteNonQuery();
                }
            }
            obsCreada = Obtener(obsACrear.Codigo);
            return obsCreada;
        }
    }
}
