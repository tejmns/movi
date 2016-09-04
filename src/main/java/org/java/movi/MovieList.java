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

@Path("myresource")
public class MovieList {
    @GET
    @Path("/{userId}")
    @Produces(MediaType.TEXT_HTML)
    public String getIt(@PathParam("userId") String id) throws SQLException {
    	Connection con=Database.connectToDatabase();
    PreparedStatement ps=null;
    ResultSet rs=null;
    ps=con.prepareStatement("select * from movies where userId='"+id+"'");

    rs=ps.executeQuery();
    String MoviesList="<html><head><style>.button {background-color: #4CAF50; border: none;color: "
    		+ "white;padding: 15px 32px; text-align: center;text-decoration: none;"
    		+ "display: inline-block;font-size: 16px;margin: 4px 2px; "
    		+ "cursor: pointer;}</style></head><body><center><table border=2><tr><td bgcolor='#FF0000'>Movie Name</td>"
    		+ "<td bgcolor='#00FF00'>Rating</td></tr>";
    int count=0;
    while(rs.next())
    {
    	MoviesList=MoviesList+"<tr><td bgcolor='#FF0000'>"+rs.getString("movieName")+"</td><td bgcolor='#00FF00'>"+rs.getInt("rating")+"</td></tr>";
    	count=count+1;;
    }
return MoviesList+ "</table><br>Total number of movies watched : "+count+"<br><a href=http://localhost:8080> <button class='button'>Home</button></a>   <br>"
		+ "</center></body><html>";
        
    }
}
