package ru.liga.karmatskiyrg.distributed.app.client;

import ru.liga.karmatskiyrg.distributed.app.lib.router.Egg;

public class SimpleApplication {

    public static void main(String[] args) {
        var egg = new Egg();

        var input = "rate --cur EUR --period week --alg old";
        egg.execute(input);

//        input = "rate --cur EUR --period week --alg old --output list";
//        egg.execute(input);
//
//        input = "rate --cur EUR --period week --alg old --output graph";
//        egg.execute(input);
    }
}
