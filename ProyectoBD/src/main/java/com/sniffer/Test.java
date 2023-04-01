package com.sniffer;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import java.io.File;
import java.time.LocalDateTime;

public class Test {
	
	public static void main(String[] args) {
		
		
		/*----------------------------------------------*/
		List<PcapIf> alldevs = new ArrayList<PcapIf>();  //Almacenar dispositivos
		
		StringBuilder errbuf = new StringBuilder();  //Para cualquier mensaje de error
		
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
	        System.err.printf("Can't read list of devices, error is %s", errbuf.toString());
	        return;
	    }
		
		System.out.println("Network devices found:");

        // Iterar todas las tarjetas de red encontradas
        int i = 0;
        for (PcapIf device : alldevs) {
            String description = (device.getDescription() != null) ? device
                    .getDescription() : "No description available";
            System.out.printf("#%d: %s [%s]\n", i++, device.getName(),
                    description);
        }
        
        
        
        
        /*----------------------------------------------*/
        
        
        PcapIf device = alldevs.get(1);
        
        System.out.printf("\nChoosing '%s' on your behalf:\n",
                (device.getDescription() != null) ? device.getDescription()
                        : device.getName());
        
        
        int snaplen = 64 * 1024; // Captura todos los paquetes, sin trucación
         
        int flags = Pcap.MODE_PROMISCUOUS;
        //System.out.println(flags);
        
        int timeout = 10 * 1000; // 10 seconds in millis
        
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
        
        if (pcap == null) {
            System.err.printf("Error while opening device for capture: "
                    + errbuf.toString());
            return;
        }else {
        	System.out.println("Puedo capturar");
        	
        	 
        }
        
        
        ///////////////////////////////////
        

        /*
        long t1 = System.currentTimeMillis();
        
        do {
        	
        	System.out.println(System.currentTimeMillis()- t1);
        	pcap.loop(1, new PacketHandler<String>(), "jNetPcap rocks!");
        	 
        	
        }while(System.currentTimeMillis()- t1 < 10000);
        
       */  
        
        pcap.loop(-1, new PacketHandler<String>(), "jNetPcap rocks!");
       
        
         
        
        
        
        
        
        pcap.breakloop();
        
        pcap.close();
        
        

        
        
        
        
        
        
        
		
	}
	
}
