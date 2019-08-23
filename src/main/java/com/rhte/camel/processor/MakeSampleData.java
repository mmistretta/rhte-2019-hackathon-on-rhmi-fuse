package com.rhte.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

import com.rhte.camel.model.JunctionInfo;
import com.rhte.camel.model.MeterInfo;

@Component
public class MakeSampleData {
	
	public void generateSamples(Exchange exchange) {
		String insert = "INSERT INTO junction_info (id,junction_name,latitude,longitude) values"
				+ "(0,'DE SOTO AV  AT  OXNARD  ST',34.1792562578955,-118.59047873659077),"
				+ "(1,'CANOGA AV AT VANOWEN ST',34.18834537329447,-118.59747110546994),"
				+ "(2,'LANFRANCO  ST  AT  LORENA  ST',34.03165357906271,-118.19850889824447),"
				+ "(3,'CRENSHAW BL AT 36th ST',34.025586946373316,-118.33511116856756),"
				+ "(4,'SLAUSON  AV  AT  NORMANDIE  AV',33.98911205158549,-118.2980275015024),"
				+ "(5,'ANAHEIM ST AT FRIES  AV',33.77990869314322,-118.2637400522642),"
				+ "(6,'FULTON  AV  AT  OXNARD  ST',34.179406259563635,-118.42252708627817),"
				+ "(7,'FOUNTAIN  AV  AT  VIRGIL  AV',34.095739886798974,-118.28673434818447),"
				+ "(8,'WOODLAKE  AV   AT   BURBANK  BL',34.171909967226455,-118.63205852433649),"
				+ "(9,'MOORPARK ST AT  CAHUENGA BL',34.150382474649966,-118.36148475510643);"
				+ "INSERT INTO meter_info (id,address,latitude,longitude) values"
				+ "(0,'1301 N LA BREA AVE',34.0954,-118.344218),"
				+ "(1,'1301 N LA BREA AVE',34.095452,-118.344219),"
				+ "(2,'1301 N LA BREA AVE',34.095041,-118.344216),"
				+ "(3,'1301 N LA BREA AVE',34.095795,-118.34422),"
				+ "(4,'1301 N LA BREA AVE',34.095847,-118.344221),"
				+ "(5,'1301 N LA BREA AVE',34.095982,-118.344223),"
				+ "(6,'1301 N LA BREA AVE',34.095644,-118.34422),"
				+ "(7,'7000 W DE LONGPRE AVE',34.096104,-118.343822),"
				+ "(8,'7000 W DE LONGPRE AVE',34.096105,-118.343668),"
				+ "(9,'7000 W DE LONGPRE AVE',34.096104,-118.343759);";
		Message in = exchange.getIn();
		in.setBody(insert);
	}

	public void insertJunction(Exchange exchange) {
		Message in = exchange.getIn();
		JunctionInfo ji = in.getBody(JunctionInfo.class);
		String insert = "INSERT INTO junction_info (id,junction_name,latitude,longitude) values"
				+ "("+ji.getId()+","+ji.getJunction_name()+","+ji.getLatitude()+","+ji.getLongitude()+");";
		in.setBody(insert);
	}
	
	public void insertMeter(Exchange exchange) {
		Message in = exchange.getIn();
		MeterInfo mi = in.getBody(MeterInfo.class);
		String insert = "INSERT INTO meter_info (id,address,latitude,longitude) values"
				+ "("+mi.getId()+","+mi.getAddress()+","+mi.getLatitude()+","+mi.getLongitude()+");";
		in.setBody(insert);
	}
}
