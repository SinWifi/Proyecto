using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SOAPServicesTest.RemesasWS;

namespace SOAPServicesTest
{
    [TestClass]
    public class RemesaTest
    {
        [TestMethod]
        public void ConsultarEstadoRemesa()
        {
            RemesasWS.RemesasClient proxy = new RemesasWS.RemesasClient();            
            Remesa remesaObtener = proxy.ObtenerRemesa(1);
            Assert.AreNotEqual(null, remesaObtener); 
        }

        [TestMethod]
        public void ObtenerRemesaByUsuario()
        {
            RemesasWS.RemesasClient proxy = new RemesasWS.RemesasClient();
            Remesa remesa = proxy.ObtenerRemesaByUsuario("dni", "45471254", 132);

            Assert.AreEqual("Pendiente", remesa.Estado);
        }

        [TestMethod]
        public void GenerarCobro()
        {
            RemesasWS.RemesasClient proxy = new RemesasWS.RemesasClient();
            Remesa remesa = proxy.GenerarCobro("dni", "45471254", 132);

            Assert.AreEqual("Cobrada", remesa.Estado);
        }

    }
}
