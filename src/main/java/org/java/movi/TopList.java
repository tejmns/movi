package org.java.movi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.java.movi.dao.Database;


@Path("top")
public class TopList {

    @GET
    @Path("/{movieId}")
    @Produces(MediaType.TEXT_HTML)
    public String getIt(@PathParam("movieId") String id) throws SQLException {
    	String[] inp=id.split(",");
    	String user=inp[0];
    	String genr=inp[1];
    	int uAge=0;
    	String resul="<html><head><style>.button {background-color: #4CAF50; border: none;color: white;padding: 15px 32px;"
    			+ " text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px; cursor: pointer;}"
    			+ "</style></head><body><center><table border=2><tr><td bgcolor='#FF0000'>Movie Name</td><td bgcolor='#00FF00'>Rating</td></tr>";
    	Connection con=Database.connectToDatabase();
    PreparedStatement ps1=null;
    PreparedStatement ps=null;
    ResultSet rs1=null;
    ResultSet rs=null;
    ps1=con.prepareStatement("select age from users where userId='"+user+"'");
    rs1=ps1.executeQuery();
    if(rs1.next())
   	uAge=rs1.getInt("age");
    rs1.close();
    ps1.close();
    int upper=uAge+5;
    int lower=uAge-5;
    
    con.close();
    con=Database.connectToDatabase();
    ps=con.prepareStatement("select movieName,avg(rating) as rate from movies  join users where users.userId=movies.userId and movies.userId!='"
    +user+"' and users.age>"+lower+" and users.age<="+upper+" and movies.genre='"+genr+"' group by moviename ORDER BY rate DESC LIMIT 5");
    rs=ps.executeQuery();
    while(rs.next())
    {
    	resul=resul+"<tr><td bgcolor='#FF0000'>"+rs.getString("movieName")+"</td><td bgcolor='00FF00'>"+rs.getDouble(2)+"</td></tr>";
    }
	return resul+ "</table><br><br><a href=http://localhost:8080> <button class='button'>Home</button></a>   <br>"
	+ "</center></body><html>";
      
    }
}

