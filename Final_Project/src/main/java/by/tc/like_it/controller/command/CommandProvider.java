package by.tc.like_it.controller.command;

import by.tc.like_it.controller.command.config.CommandName;
import by.tc.like_it.controller.command.impl.MainPageCommand;
import by.tc.like_it.controller.command.impl.answer.AddAnswerJSON;
import by.tc.like_it.controller.command.impl.answer.GetAnswersJSON;
import by.tc.like_it.controller.command.impl.authorization.SignInCommand;
import by.tc.like_it.controller.command.impl.authorization.SignOutCommand;
import by.tc.like_it.controller.command.impl.authorization.SignUpCommand;
import by.tc.like_it.controller.command.impl.initialization.DestroySourceCommand;
import by.tc.like_it.controller.command.impl.initialization.InitSourceCommand;
import by.tc.like_it.controller.command.impl.localization.ChangeLocaleCommand;
import by.tc.like_it.controller.command.impl.question.*;
import by.tc.like_it.controller.command.impl.tag.*;
import by.tc.like_it.controller.command.impl.user.BanUserCommand;
import by.tc.like_it.controller.command.impl.user.EditUserCommand;
import by.tc.like_it.controller.command.impl.user.GetUserJSON;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private static final Logger LOGGER = Logger.getLogger(CommandProvider.class);

    private static final CommandProvider instance = new CommandProvider();
    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider(){
        commands.put(CommandName.REDIRECT_TO_MAIN_PAGE, new MainPageCommand());

        commands.put(CommandName.SIGN_UP, new SignUpCommand());
        commands.put(CommandName.SIGN_IN, new SignInCommand());
        commands.put(CommandName.SIGN_OUT, new SignOutCommand());
        commands.put(CommandName.EDIT_USER, new EditUserCommand());

        commands.put(CommandName.INIT_SOURCE, new InitSourceCommand());
        commands.put(CommandName.DESTROY_SOURCE, new DestroySourceCommand());

        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());

        commands.put(CommandName.GET_QUESTION_JSON, new GetQuestionJSON());
        commands.put(CommandName.ADD_QUESTION, new AddQuestionCommand());
        commands.put(CommandName.EDIT_QUESTION, new EditQuestionCommand());

        commands.put(CommandName.ADD_ANSWER_JSON, new AddAnswerJSON());


        commands.put(CommandName.ADD_TAG, new AddTagJSON());
        commands.put(CommandName.DELETE_TAG, new DeleteTagCommand());

        commands.put(CommandName.GET_TAGS_JSON, new GetTagsJSON());
        commands.put(CommandName.GET_QUESTIONS_JSON, new GetQuestionsJSON());
        commands.put(CommandName.GET_ANSWERS_JSON, new GetAnswersJSON());
        commands.put(CommandName.GET_USER_JSON, new GetUserJSON());
        commands.put(CommandName.BAN_USER, new BanUserCommand());
    }

    public static CommandProvider getInstance(){
        return instance;
    }

    public Command getCommand(String name){
        Command command;
        try {
            if (name != null) {
                CommandName commandName = CommandName.valueOf(name.toUpperCase().replace('-', '_'));
                command = commands.get(commandName);
            } else {
                command = commands.get(CommandName.REDIRECT_TO_MAIN_PAGE);
            }
        } catch (IllegalArgumentException  e) {
            LOGGER.warn(e);
            command = commands.get(CommandName.REDIRECT_TO_MAIN_PAGE);
        }
        return command;
    }
}
