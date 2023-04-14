package ru.liga.karmatskiyrg.distributed.app.client;

import lombok.extern.slf4j.Slf4j;
import ru.liga.karmatskiyrg.distributed.app.lib.router.Egg;

@Slf4j
public class SimpleApplication {

    public static void main(String[] args) {
        var egg = new Egg();
        String input;
        Object res;

//        input = "test --cur EUR";
//        egg.execute(input);

        input = "rate --cur EUR --period week --alg old";
        res = egg.execute(input);
        log.debug("{}", res);

//        input = "rate --cur EUR --date 10.05.2001 --alg old";
//        egg.execute(input);

//        input = "rate --cur EUR --period week --alg old --output list";
//        egg.execute(input);
//
//        input = "rate --cur EUR --period week --alg old --output graph";
//        egg.execute(input);
    }
}
