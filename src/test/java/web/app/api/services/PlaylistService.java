package web.app.api.services;

import io.restassured.response.Response;
import web.app.api.objects.Playlist;

import static io.restassured.RestAssured.given;

public class PlaylistService {
    private String basePlaylistEndpoint = "http://localhost:8080/api/playlists";
    private String playListEndpointWithId = "http://localhost:8080/api/playlists/{id}";

    private String addingTrackToPlaylistEndpoint = "http://localhost:8080/api/playlists/{id}/tracks/add";

    private String deletingTrackFromPlayListEndpoint = "http://localhost:8080/api/playlists/{id}/tracks/remove";



    public Response createPlaylistWithParams(String description, boolean isPublic, String name, int userId) {
        Playlist body = new Playlist("description", true, "myPlaylist", 1);
        Response response = given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post(basePlaylistEndpoint);
        return response;
    }

    public  Response getPlaylistById(int id) {
        return given().
                pathParam("id", id).
                when().
                get(playListEndpointWithId);
    }


    public Response updatePlaylist(int id, Playlist playlist) {
        return given().
                pathParam("id", id).
                header("Content-Type", "application/json").
                body(playlist).
                when().
                put(playListEndpointWithId);
    }

    public  Response addTrackToPLaylist(int playlistId, int trackId) {
        String body = "{\"trackId\":" + trackId + "}";
        return given().
                pathParam("id", playlistId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post(addingTrackToPlaylistEndpoint);
    }
    public Response removeTrackFromPlaylist(int playlistId, int trackId) {
        String body = "{\"trackId\":" + trackId + "}";
        return given().
                pathParam("id", playlistId).
                header("Content-Type", "application/json").
                body(body).
                when().
                delete(deletingTrackFromPlayListEndpoint);
    }
    public Response deletePlaylist(int id) {
        return given().
                pathParam("id", id).
                when().
                delete(playListEndpointWithId);
    }
}
