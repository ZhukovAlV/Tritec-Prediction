package com.example.mySite.controller;

import com.example.mySite.entity.Prediction;
import com.example.mySite.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.Random;

@Controller
public class MainController {

    private final PredictionService predictionService;

    @Autowired
    public MainController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

   /* @GetMapping("/prediction")
    public String greetMessage(Model model) {
        String[] predictions = new String[]{
                "Да",
                "Нет",
                "Не знаю",
                "Скорее да",
                "Скорее нет, чем да",
                "Скорее да, чем нет",
                "Вероятно",
                "Вполне возможно",
                "Затрудняюсь ответить",
                "Даже и не думай",
                "Мечтать не вредно"
        };

        Random random = new Random();
        int num = random.nextInt(10);
        model.addAttribute("message", predictions[num]);
        return "prediction";
    }*/

    @GetMapping("/prediction")
    public String greetMessage(Model model) {
        Random random = new Random();
        long num = random.nextInt(10) + 1;

        Optional<Prediction> predictionOp = predictionService.getPredictionById(num);
        if (predictionOp.isPresent()) model.addAttribute("message", predictionOp.get().getName());
        else model.addAttribute("message", "Предсказание для Вас не нашлось");

        return "prediction";
    }

}
