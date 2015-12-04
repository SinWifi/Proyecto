using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace DSD.Entidades
{
    [DataContract]
    class Observacion
    {
        [DataMember]
        public int ID { get; set; }
        [DataMember]
        public Remesa remesa { get; set; }
        [DataMember]
        public string descripcion { get; set; }
        [DataMember]
        public string fecha { get; set; }

    }
}
