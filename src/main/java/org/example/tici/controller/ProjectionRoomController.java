package org.example.tici.controller;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.example.tici.Service.ProjectionRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class ProjectionRoomController {

    @Autowired
    ProjectionRoomService projectionRoomService;
    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody ProjectionRoom room) {
        try {
            projectionRoomService.addRoom(room);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
