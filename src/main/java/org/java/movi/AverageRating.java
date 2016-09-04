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


@Path("avgrating")
public class AverageRating {

    @GET
    @Path("/{movieId}")
    @Produces(MediaType.TEXT_HTML)
    public String getIt(@PathParam("movieId") String id) throws SQLException {
   	Connection con=Database.connectToDatabase();
    PreparedStatement ps=null;
    ResultSet rs=null;
    ps=con.prepareStatement("select movieName,avg(rating) from movies where movieId='"+id+"'");
    rs=ps.executeQuery();
    String MovieRating="";
    int count=0;
    while(rs.next())
    {
    	MovieRating=MovieRating+rs.getString("movieName")+":   "+rs.getDouble("avg(rating)")+"<br>";
    	count=count+1;;
    }


    return "<html><head><style>.button {background-color: #4CAF50; border: none;color: white;padding: 15px 32px; text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px; cursor: pointer;}</style><body><center><h1>"+MovieRating+ "<a href=http://localhost:8080><button class='button'>Home</button></a>   <br>"
    	+ "<center></body></html>";

        
    }
}