package com.example.mySite.controller;

import com.example.mySite.entity.Prediction;
import com.example.mySite.repository.PredictionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.Random;

@Controller
public class MainController {


    @Autowired
    private PredictionRepo predictionRepo;

    @GetMapping("/prediction")
    public String greetMessage(Model model) {
        Random random = new Random();
        long num = random.nextInt(10) + 1;

        Optional<Prediction> predictionOp = predictionRepo.findById(num);
        if (predictionOp.isPresent()) model.addAttribute("message", predictionOp.get().getName());
        else model.addAttribute("message", "Предсказание для Вас не нашлось");

        return "prediction";
    }

}
