using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Net;
using System.IO;
using System.Web.Script.Serialization;
using System.Text;

namespace RESTServicesTest
{
    [TestClass]
    public class ObservacionTest
    {
        private Remesa obtenerRemesa(String numero)
        {
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create("http://localhost:61010/Remesas.svc/Remesas/"+numero);
            req.Method = "GET";
            HttpWebResponse res = (HttpWebResponse)req.GetResponse();
            StreamReader reader = new StreamReader(res.GetResponseStream());
            String cadenaJson = reader.ReadToEnd();
            JavaScriptSerializer js = new JavaScriptSerializer();
            Remesa remesa = js.Deserialize<Remesa>(cadenaJson);
            return remesa;
        }

        [TestMethod]
        public void CrearObservacion()
        {
            Observacion observacion = new Observacion();
            observacion.fecha = "04/12/2015";
            observacion.descripcion = "No hay respuesta hace varios días";
            observacion.remesa = obtenerRemesa("1");

            //string postdata = "{\"descripcion\":\"No hay respuesta hace varios días\",\"fecha\":\"04/12/2015\",\"remesa\":{id}}";
            JavaScriptSerializer js = new JavaScriptSerializer();
            string postdata = js.Serialize(observacion);
            byte[] data = Encoding.UTF8.GetBytes(postdata);
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://localhost:61010/Observaciones.svc/Observaciones");
            request.Method = "POST";
            request.ContentLength = data.Length;
            request.ContentType = "application/json";
            var reqStream = request.GetRequestStream();
            reqStream.Write(data, 0, data.Length);

            HttpWebResponse response = null;
            try
            {
                response = (HttpWebResponse)request.GetResponse();
                StreamReader reader = new StreamReader(response.GetResponseStream());
                string cadenaJson = reader.ReadToEnd();                
                String mensaje = js.Deserialize<String>(cadenaJson);
                Assert.AreEqual("Su observación ha sido enviada correctamente", mensaje);
            }
            catch (WebException e)
            {
                HttpStatusCode code = ((HttpWebResponse)e.Response).StatusCode;
                String description = ((HttpWebResponse)e.Response).StatusDescription;
                StreamReader readerCatch = new StreamReader(e.Response.GetResponseStream());
                String cadenaJsonCatch = readerCatch.ReadToEnd();
                JavaScriptSerializer jsCatch = new JavaScriptSerializer();
                String mensaje = jsCatch.Deserialize<String>(cadenaJsonCatch);
                Assert.AreEqual("No es posible el envío, remesa CANCELADA", mensaje);
            }
        }


        [TestMethod]
        public void CrearObservacionRemesaCancelada()
        {
            Observacion observacion = new Observacion();
            observacion.fecha = "04/12/2015";
            observacion.descripcion = "No hay respuesta hace varios días";
            observacion.remesa = obtenerRemesa("4");

            //string postdata = "{\"descripcion\":\"No hay respuesta hace varios días\",\"fecha\":\"04/12/2015\",\"remesa\":{id}}";
            JavaScriptSerializer js = new JavaScriptSerializer();
            string postdata = js.Serialize(observacion);
            byte[] data = Encoding.UTF8.GetBytes(postdata);
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://localhost:61010/Observaciones.svc/Observaciones");
            request.Method = "POST";
            request.ContentLength = data.Length;
            request.ContentType = "application/json";
            var reqStream = request.GetRequestStream();
            reqStream.Write(data, 0, data.Length);

            HttpWebResponse response = null;
            try
            {
                response = (HttpWebResponse)request.GetResponse();
                //StreamReader reader = new StreamReader(response.GetResponseStream());
                //string cadenaJson = reader.ReadToEnd();
                //String mensaje = js.Deserialize<String>(cadenaJson);
                //Assert.AreEqual("Su observación ha sido enviada correctamente", mensaje);
            }
            catch (WebException e)
            {
                HttpStatusCode code = ((HttpWebResponse)e.Response).StatusCode;
                String description = ((HttpWebResponse)e.Response).StatusDescription;
                StreamReader readerCatch = new StreamReader(e.Response.GetResponseStream());
                String cadenaJsonCatch = readerCatch.ReadToEnd();
                JavaScriptSerializer jsCatch = new JavaScriptSerializer();
                String mensaje = jsCatch.Deserialize<String>(cadenaJsonCatch);
                Assert.AreEqual("No es posible el envío, remesa CANCELADA", mensaje);
            }
        }
    }
}
