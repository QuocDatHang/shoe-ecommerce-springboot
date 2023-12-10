package com.example.controller.api;

import com.example.Model.Color;
import com.example.service.color.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/colors")
public class ColorAPI {
    @Autowired
    private ColorService colorService;
    @GetMapping
    public ResponseEntity<?> getAllColors() {
        List<Color> colors = colorService.findAll();
        return new ResponseEntity<> (colors, HttpStatus.OK);
    }
}
