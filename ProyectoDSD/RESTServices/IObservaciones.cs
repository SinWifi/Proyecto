using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RESTServices
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IObservaciones" en el código y en el archivo de configuración a la vez.
    [ServiceContract]
    public interface IObservaciones
    {
        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "Observaciones", ResponseFormat = WebMessageFormat.Json)]
        String CrearObservacion(Observacion observacion);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "Observaciones", ResponseFormat = WebMessageFormat.Json)]
        List<Observacion> ListarObservaciones();
    }
}
