package com.marcelo.spotify.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.spotify.client.Album;
import com.marcelo.spotify.client.AlbumSpotifyClient;
import com.marcelo.spotify.client.AuthSpotifyClient;
import com.marcelo.spotify.client.LoginRequest;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient,
                           AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/cds")
    public ResponseEntity<List<Album>> helloWorld() {

		var request = new LoginRequest(
				"client_credentials",
				"6a18b807dd1a415c9b884a65da01d844",
				"2450d75da1434e329a6a974cd0b908b9"
				);
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);


        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
