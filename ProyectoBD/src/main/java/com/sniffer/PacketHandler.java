package com.sniffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
//import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.jnetpcap.protocol.voip.Rtp;
import org.w3c.dom.ls.LSOutput;

import com.dao.CapturaDAO;
import com.dao.Captura_has_ProtocoloDAO;
import com.dao.ConsultaDAO;
import com.dao.PaqueteDAO;
import com.dao.ProtocoloDAO;
import com.dao.ServicioDAO;
import com.model.Captura;
import com.model.Captura_has_Protocolo;
import com.model.Paquete;
import com.model.Protocolo;
import com.model.Servicio;

import org.jnetpcap.protocol.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
 


 

public class PacketHandler<T> implements PcapPacketHandler<T>  {
	
	private Http http = new Http();
	private Ethernet eth = new Ethernet(); 
	private Ip4 ip = new Ip4();
	private Ip6 ip6 = new Ip6();
	private Tcp tcp = new Tcp();
	private Udp udp = new Udp();
	private Rtp rtp = new Rtp();
	Sniffer s = new Sniffer();
	Date fecha = new Date();
 
 
 

	public void guardar(Captura cap, int servicio, int Enlace, int Red, int Transporte, int Longitud, String fecha) throws SQLException {
		
		ConsultaDAO c = new ConsultaDAO();
		CapturaDAO capDao =  new CapturaDAO();
		
		 
        try {
			capDao.guardar(cap);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        int id = capDao.getLastID();
		System.out.println(id);

       
        Captura_has_Protocolo capP = new Captura_has_Protocolo();
        
        capP.setId_captura(id);
        capP.setId_servicio(servicio);
        capP.setId_protocolo(Enlace);
         
        guardarProtocolo(capP);
        
        capP.setId_captura(id);
        capP.setId_servicio(servicio);
        capP.setId_protocolo(Red);
        
        guardarProtocolo(capP);
        
        capP.setId_captura(id);
        capP.setId_servicio(servicio);
        capP.setId_protocolo(Transporte);
        
        guardarProtocolo(capP);
        
       
        Paquete p = new Paquete();
        
        p.setLongitud(Longitud);
        p.setFechaCaptura(fecha);
        p.setId_captura(id);
        p.setId_consulta(c.getLastID());
        p.setId_servicio(servicio);
        
        System.out.println(p.toString());
        
        guardarPaquete(p);
        
       
        

	}
	
	public void guardarProtocolo(Captura_has_Protocolo cp) {
		
		Captura_has_ProtocoloDAO cpDao = new Captura_has_ProtocoloDAO();
		
		 try {
			 cpDao.guardar(cp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public void guardarPaquete(Paquete p) throws SQLException {
		PaqueteDAO pc = new PaqueteDAO();
		
		pc.guardar(p);
	}

    @Override
    public void nextPacket(PcapPacket packet, T user) {
    	
    	
    	
    	StringBuilder errbuf = new StringBuilder();

   
    	/* 
    	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
		 
			e1.printStackTrace();
		}
    	*/

    	if(packet == null) return;
     
    	
    	/*
    	 
        if (!packet.hasHeader(tcp)) {
            return;
        }
        */
        
        
        System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
        new Date(packet.getCaptureHeader().timestampInMillis()), packet.getCaptureHeader().caplen(),packet.getCaptureHeader().wirelen(),user); 
       
        
        String contend = packet.toString();
        if (contend.contains("DDDDD")&&contend.contains("upass")) {
            System.out.println(contend);
        }
        
        
 
        //System.out.println(contend);
        //SACAR LOS OBJETOS
        
        int longitud =  packet.getCaptureHeader().caplen();
        
        Date fechaCaptura =new Date(packet.getCaptureHeader().timestampInMillis()); 
        String ob = String.valueOf(fechaCaptura);
        
        
        //System.out.println("Longitud " + longitud);
        //System.out.println("Fecha Paquete " + ob);
        
       
        
        int ProtocoloEnlace = 0;
        int ProtocoloRed = 0;
        int ProtocoloTransporte = 0;
        
        
        int puertoOrigen = 0;
        int puertoDestino = 0;
        int id_Servicio = 0;
        String direccionFisica = "";
        String direccion_source = "";
        String direccion_destino = "";
        
        
        if(packet.hasHeader(eth)) {
        	ProtocoloEnlace = 1;
        	//System.out.println("source MAC " + FormatUtils.mac(packet.getHeader(eth).source()));
        	direccionFisica = FormatUtils.mac(packet.getHeader(eth).source());
        }
        
        if(packet.hasHeader(ip)) {
        	ProtocoloRed = 2;
        	System.out.println("source ip4: " + FormatUtils.ip(packet.getHeader(ip).source()));	 
        	System.out.println("destino ip4: " + FormatUtils.ip(packet.getHeader(ip).destination()));
        	direccion_source = FormatUtils.ip(packet.getHeader(ip).source());
        	direccion_destino = FormatUtils.ip(packet.getHeader(ip).destination());
        }
        
      
         
        if(packet.hasHeader(ip6)) {
        	ProtocoloRed = 2;
        	System.out.println("source ip6: " + FormatUtils.ip(packet.getHeader(ip6).source()));	 
        	System.out.println("destino ip46: " + FormatUtils.ip(packet.getHeader(ip6).destination()));	
        	
        	direccion_source = FormatUtils.ip(packet.getHeader(ip6).source());
        	direccion_destino = FormatUtils.ip(packet.getHeader(ip6).destination());
        }
        

        if(packet.hasHeader(tcp)) {
        	ProtocoloTransporte = 4;
        	System.out.println("source tcp: " +  packet.getHeader(tcp).source());
        	System.out.println("destino tcp: " + packet.getHeader(tcp).destination());
        	puertoDestino = packet.getHeader(tcp).destination();
        	puertoOrigen =packet.getHeader(tcp).source();
        }
        
        if(packet.hasHeader(udp)) {
        	ProtocoloTransporte = 3;
        	System.out.println("source udp: " +  packet.getHeader(udp).source());
        	System.out.println("destino udp " + packet.getHeader(udp).destination());
        	puertoDestino = packet.getHeader(udp).destination();
        	puertoOrigen =packet.getHeader(udp).source();
        }
        	
        
        
        
        if(direccion_source.equals(" ")) return;
        
        /*
   
        int servicio = EncontrarServicio(puertoOrigen);
        if(!servicio)
            servicio = EncontrarServicio(destino);

        String nombreServicio = TraducirServicio(servicio);
        */
        
  
        
        String servicio = Sniffer.encontarServicio(puertoOrigen, puertoDestino);
        System.out.println("Servicio : "  + servicio);
  
        switch(servicio) {
        
	        case "HTTP": id_Servicio = 1;break;
	        case "FTP": id_Servicio = 2;break;
	        case "DNS": id_Servicio = 3;break;
	        case "SMTP": id_Servicio = 4;break;
	        case "POP3": id_Servicio = 5;break;
	        case "DHCP": id_Servicio = 6;break;
	        case "HTTPS": id_Servicio = 7;break;
	        case "FTPS": id_Servicio = 8;break;
	        case "Otro": id_Servicio = 9;break; 
	        
        }
        
        System.out.println("id_servicio = " + id_Servicio);
        
        Captura cap = new Captura();
         
        cap.setDireccionFisica(direccionFisica);
        cap.setDireccionIp_source(direccion_source);
        cap.setDireccionIp_destino(direccion_destino);
        cap.setPuertoDestino(String.valueOf(puertoDestino));
        cap.setId_servicio(id_Servicio);

        try {
			guardar(cap,id_Servicio,ProtocoloEnlace,ProtocoloRed,ProtocoloTransporte, longitud,ob);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        
        
        
        //Captura_has_Protocolo capP1 = new Captura_has_Protocolo();
         
     
        
        
        
        
        
        
        
        
       
        
       
        
        
        
        
        
        
        
    }
    
    public static String TraducirServicio(int servicio)
    {
        String nombre = "";

        switch(servicio)
        {
            case 0:
                nombre = "Otro";
                break;
            case 1:
                nombre = "FTP";
                break;
            case 2:
                nombre = "HTTP";
                break;
            case 3:
                nombre = "DNS";
                break;
            case 4:
                nombre = "SMTP";
                break;
            case 5:
                nombre = "POP3";
                break;
            case 6:
                nombre = "DHCP";
                break;
            case 7:
                nombre = "HTTPS";
                break;
            case 8:
                nombre = "FTPS";
                break;
        }

        return nombre;
    }

	
	

}
