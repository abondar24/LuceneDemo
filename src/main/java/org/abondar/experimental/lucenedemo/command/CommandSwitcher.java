package org.abondar.experimental.lucenedemo.command;


import org.abondar.experimental.lucenedemo.command.impl.AnalyzerCommand;
import org.abondar.experimental.lucenedemo.command.impl.BooleanQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.ComplexPhraseQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.FileIndexerCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexTuningCommand;
import org.abondar.experimental.lucenedemo.command.impl.IndexerCommand;
import org.abondar.experimental.lucenedemo.command.impl.MultifieldQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.PhraseQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.PrefixQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.SearchCommand;
import org.abondar.experimental.lucenedemo.command.impl.SortingCommand;
import org.abondar.experimental.lucenedemo.command.impl.SpanNearQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.SpanTermQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.TermQueryCommand;
import org.abondar.experimental.lucenedemo.command.impl.WildcardQueryCommand;

public class CommandSwitcher {

    private final CommandExecutor executor;

    public CommandSwitcher() {
        this.executor = new CommandExecutor();
    }


    public void executeCommand(String cmd){
        try {
            switch (Commands.valueOf(cmd)){

                case AC:
                    AnalyzerCommand ac = new AnalyzerCommand();
                    executor.executeCommand(ac);
                    break;

                case BQC:
                    BooleanQueryCommand bqc = new BooleanQueryCommand();
                    executor.executeCommand(bqc);
                    break;

                case CPQC:
                    ComplexPhraseQueryCommand cpqc = new ComplexPhraseQueryCommand();
                    executor.executeCommand(cpqc);

                case FIC:
                    FileIndexerCommand fic = new FileIndexerCommand();
                    executor.executeCommand(fic);
                    break;


                case IC:
                    IndexerCommand ic = new IndexerCommand();
                    executor.executeCommand(ic);
                    break;

                case ITC:
                    IndexTuningCommand itc = new IndexTuningCommand();
                    executor.executeCommand(itc);
                    break;

                case MQC:
                    MultifieldQueryCommand mqc = new MultifieldQueryCommand();
                    executor.executeCommand(mqc);
                    break;

                case PQC:
                    PhraseQueryCommand pqc = new PhraseQueryCommand();
                    executor.executeCommand(pqc);
                    break;

                case PRQC:
                    PrefixQueryCommand prqc = new PrefixQueryCommand();
                    executor.executeCommand(prqc);
                    break;

                case SC:
                    SearchCommand sc = new SearchCommand();
                    executor.executeCommand(sc);
                    break;

                case SNQC:
                    SpanNearQueryCommand snqc = new SpanNearQueryCommand();
                    executor.executeCommand(snqc);
                    break;

                case SOC:
                    SortingCommand soc = new SortingCommand();
                    executor.executeCommand(soc);
                    break;

                case STQC:
                    SpanTermQueryCommand stqc = new SpanTermQueryCommand();
                    executor.executeCommand(stqc);
                    break;

                case TQC:
                    TermQueryCommand tqc = new TermQueryCommand();
                    executor.executeCommand(tqc);
                    break;

                case WQC:
                    WildcardQueryCommand wqc = new WildcardQueryCommand();
                    executor.executeCommand(wqc);
                    break;

            }

        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
