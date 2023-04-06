package ru.liga.karmatskiyrg.controller.telergam.output;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.liga.karmatskiyrg.controller.telergam.output.interfaces.OutputController;
import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
import ru.liga.karmatskiyrg.utils.graphics.RateChart;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GraphOutputController implements OutputController {
    @Override
    public Object get(List<CurrencyRate> rates, Long chatId) {
//        var msg = new SendMessage();

        try {
            var msg = new SendPhoto();
            msg.setChatId(chatId);
            RateChart example = new RateChart();
            var image = example.createChart(rates).createBufferedImage(1000, 1000);
            var os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os); // можно указать другой формат изображения
            var inputFile = new InputFile(new ByteArrayInputStream(os.toByteArray()), "image.png");
            msg.setPhoto(inputFile);
            return msg;
        } catch (IOException e) {
            var msg = new SendMessage();
            msg.setChatId(chatId);
            msg.setText(e.getMessage());
            return msg;
        }
    }
}
