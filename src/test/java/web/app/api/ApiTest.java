package web.app.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.app.api.objects.Playlist;
import web.app.api.services.PlaylistService;

import static org.hamcrest.Matchers.*;

public class ApiTest {
    PlaylistService playlistService;
    Playlist playlist;
    Response createdPlaylist;
    int playlistId;

    @BeforeEach
    public void init() {
        playlistService = new PlaylistService();
        createdPlaylist = playlistService.
                createPlaylistWithParams("description", true, "myPlaylist", 1);
        playlist = createdPlaylist.as(Playlist.class);
        playlistId = playlist.getId();
    }

    @Test
    public void testCreatePlaylist() {
        createdPlaylist.
            then().
                assertThat().
                    statusCode(201);
        playlistService.getPlaylistById(playlistId).
            then().
                assertThat().
                    statusCode(200).
                    body("id", equalTo(playlistId)).
                    body("description", equalTo(playlist.getDescription())).
                    body("isPublic", equalTo(playlist.isPublic())).
                    body("name", equalTo(playlist.getName())).
                    body("userId", equalTo(playlist.getUserId()));
    }

    @Test
    public void testModifyPlaylist() {
        Playlist newValues = new Playlist("newDescription", false, "myNewPlaylist", playlist.getUserId());
        playlistService.updatePlaylist(playlistId, newValues).
            then().
                assertThat().
                    statusCode(200).
                    body("id", equalTo(playlistId)).
                    body("description", equalTo(newValues.getDescription())).
                    body("isPublic", equalTo(newValues.isPublic())).
                    body("name", equalTo(newValues.getName())).
                    body("userId", equalTo(newValues.getUserId()));
        playlistService.getPlaylistById(playlistId).
            then().
                assertThat().
                    statusCode(200).
                    body("id", equalTo(playlistId)).
                    body("description", equalTo(newValues.getDescription())).
                    body("isPublic", equalTo(newValues.isPublic())).
                    body("name", equalTo(newValues.getName())).
                    body("userId", equalTo(newValues.getUserId()));
    }

    @Test
    public void testAddTracksToPlaylist() {
        playlistService.addTrackToPLaylist(playlistId, 1).
            then().
                assertThat().
                    statusCode(200).
                    body("tracks.size()", equalTo(1)).
                    body("tracks.id", everyItem(notNullValue())).
                    body("tracks.id[0]", equalTo(1));
        playlistService.getPlaylistById(playlistId).then().assertThat().statusCode(200);
    }

    @Test
    public void testRemoveTracksFromPlaylist(){
        playlistService.addTrackToPLaylist(playlistId, 1).
            then().
                assertThat().
                statusCode(200).
                body("tracks.size()", equalTo(1));
        playlistService.removeTrackFromPlaylist(playlistId, 1).
            then().
                assertThat().
                    statusCode(200).
                    body("tracks.size()", equalTo(0));
    }

    @Test
    public void testDeletePlaylist(){
        playlistService.deletePlaylist(playlistId).
            then().
                assertThat().
                    statusCode(200);
        playlistService.getPlaylistById(playlistId).
            then().
                assertThat().
                    statusCode(404);
    }
}
