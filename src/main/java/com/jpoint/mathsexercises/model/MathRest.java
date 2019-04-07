package com.jpoint.mathsexercises.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;


@RestController
public class MathRest {

    private final Random random = new Random();

    @GetMapping("/{q}")
    public List<MathExercise> getExercises(@PathVariable int q,
                                           @RequestParam(required = false, name = "level", defaultValue = "100") int level) {
        return generateExercises(q, level);
    }

    private List<MathExercise> generateExercises(int q, int level) {
        return IntStream.range(0, q)
                .mapToObj(i -> {
                    final Character operation = odd(i) ? '+' : '*';
                    final long a = random.nextInt(level - level / 10) + level / 10;
                    final long b = random.nextInt(level - level / 10) + level / 10;

                    final long answer = operation.equals('+') ? a + b : a * b;
                    final String question = valueOf(a) + operation + b;

                    return new MathExercise(question, valueOf(answer));
                }).collect(toList());
    }

    private static boolean odd(int num) {
        return num % 2 == 0;
    }

}
