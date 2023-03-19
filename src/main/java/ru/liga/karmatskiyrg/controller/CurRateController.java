//package ru.liga.karmatskiyrg.controller;
//
//import ru.liga.karmatskiyrg.controller.errors.NotValidCommand;
//import ru.liga.karmatskiyrg.controller.init.Init;
//import ru.liga.karmatskiyrg.controller.observers.CommandLeadAction;
//import ru.liga.karmatskiyrg.controller.observers.ParameterLeadAction;
//import ru.liga.karmatskiyrg.controller.observers.dicts.IsCurrencyString;
//import ru.liga.karmatskiyrg.controller.observers.dicts.IsLineCommand;
//import ru.liga.karmatskiyrg.controller.observers.dicts.IsLineParameter;
//import ru.liga.karmatskiyrg.model.currency.CurrencyRate;
//import ru.liga.karmatskiyrg.model.dicts.interfaces.DCurrencyType;
//import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineCommand;
//import ru.liga.karmatskiyrg.model.dicts.interfaces.DLineParameter;
//import ru.liga.karmatskiyrg.utils.parse.ParseCommandLine;
//import ru.liga.karmatskiyrg.views.basic.EmptyView;
//import ru.liga.karmatskiyrg.views.basic.ExceptionView;
//import ru.liga.karmatskiyrg.views.interfaces.View;
//
//import java.util.List;
//
//public class CurRateController {
//    static {
//        Init.init();
//    }
//
//    protected DLineCommand command;
//    protected DLineParameter parameter;
//    protected DCurrencyType currencyType;
//
//    protected View view = new EmptyView();
//
//    protected CommandLeadAction commandObserver = CommandLeadAction.getSingleton();
//    protected ParameterLeadAction parameterObserver = ParameterLeadAction.getSingleton();
//
//
//
//
//    protected void parseArgs(String commandLine) throws NotValidCommand {
//        var args = ParseCommandLine.parseCommand(commandLine);
//
//        if (args.size() != 3)
//            throw new NotValidCommand("Invalid number of arguments");
//
//        var command = IsLineCommand.getSingleton().getFirstVariant(args.get(0));
//        var currencyType = IsCurrencyString.getSingleton().getFirstVariant(args.get(1));
//        var parameter = IsLineParameter.getSingleton().getFirstVariant(args.get(3));
//
//        if (command == null)
//            throw new NotValidCommand(String.format("Command %s not found", args.get(0)));
//
//        if (currencyType == null)
//            throw new NotValidCommand(String.format("Currency %s not found", args.get(1)));
//
//        if (parameter == null)
//            throw new NotValidCommand(String.format("Parameter %s not found", args.get(3)));
//
//        this.command = command;
//        this.currencyType = currencyType;
//        this.parameter = parameter;
//    }
//
//    public void currencyRates(String args) {
//        try {
//            this.parseArgs(args);
//            var func = this.commandObserver.getFirstVariant(this.command);
//            this.view = func == null ? new EmptyView() : func.call();
//
////            this.view = switch ((DLineCommands) this.command) {
////                case RATE -> this.predict(this.currencyType, this.parameter);
////                default -> new EmptyView();
////            };
//        } catch (Exception e) {
//            this.view = new ExceptionView(e);
//        }
//    }
//
//    public void show() {
//        this.view.show();
//    }
//
//    protected View predict(DLineParameter parameter, DCurrencyType type) {
////        var res = switch ((DLineParameters) parameter) {
////            case TMR -> new ArrayList<>(new PredictCurrencyRate().getCurrencyRateTomorrow(type));
////            case WEK -> new PredictCurrencyRate().getCurrencyRateWeek(type);
//////            default -> new ArrayList<>();
////        };
//
//        List<CurrencyRate> rate = null;
//        return this.parameterObserver.getFirstVariant(parameter).apply(type);
////        if (parameter == DLineParameters.TMR)
////            rate = new ArrayList<>() {{
////                add(new PredictCurrencyRate().getCurrencyRateTomorrow(type));
////            }};
////        if (parameter == DLineParameters.WEK)
////            rate = new PredictCurrencyRate().getCurrencyRateWeek(type);
////
////        return new CurrencyView(rate);
//    }
//}
