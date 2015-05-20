package b.czlapinski.LastFM_RestAssured;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;


public class Testy{

	public static String format;
	public static RequestSpecification spec;
	public static RequestSpecification specLimit;
	
	@BeforeClass
	public static void SetUp(){
		RestAssured.baseURI = "http://ws.audioscrobbler.com/2.0/";
	}
	
	//testy najpopularniejszych w kraju mogą się zmienić tzn. wyniki
	
	@Test
	public void TheBestCD(){
	given().params("artist", "Christopher Tin", "api_key", "36ee275bb37a084000da7b7ec05d6c08", "format", "json","limit", 1)
	.when().get("?method=artist.getTopAlbums")
	.then().body("topalbums.album.name", equalTo("Calling All Dawns"));}
	
	@Test
	public void ArtistInfo(){
	given().params("artist", "Christopher Tin", "api_key", "36ee275bb37a084000da7b7ec05d6c08", "format", "json","limit", 1)
	.when().get("?method=artist.getInfo")
	.then().body("artist.url", equalTo("http://www.last.fm/music/Christopher+Tin"));}
	
	@Test
	public void AlbumSim(){
	given().params("artist", "Christopher Tin" ,"api_key", "36ee275bb37a084000da7b7ec05d6c08", "format", "json","limit", 1)
	.when().get("?method=artist.getsimilar")
	.then().body("similarartists.artist.name", equalTo("Stereo Alchemy"));}

	@Test
	public void CountryTopAlbum(){
	given().params("country", "Poland" ,"api_key", "36ee275bb37a084000da7b7ec05d6c08", "format", "json","limit", 1)
	.when().get("?method=geo.getTopArtists")
	.then().body("topartists.artist.name", equalTo("Arctic Monkeys"));}
	
	@Test 
	public void CountryTopTracks(){
	given().params("country", "Poland" ,"api_key", "36ee275bb37a084000da7b7ec05d6c08", "format", "json","limit", 1)
	.when().get("?method=geo.getTopTracks")
	.then().body("toptracks.track.name", equalTo("youtube.com/devicesupport"));}
	
	
}
