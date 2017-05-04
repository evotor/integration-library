package ru.evotor.framework.core.action.event.receipt.before_position_added;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ExtraKeyMapper;
import ru.evotor.framework.receipt.ExtraKey;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */
public class BeforePositionAddedEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_COMMANDS = "commands";
    private static final String KEY_COMMAND_TYPE = "type";
    private static final String KEY_COMMAND = "command";


    public static BeforePositionAddedEventResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        Parcelable[] commandsParcelable = bundle.getParcelableArray(KEY_COMMANDS);
        List<ICommand> commands = new ArrayList<>();
        for (int i = 0; i < commandsParcelable.length; i++) {
            ICommand command = createCommand((Bundle) commandsParcelable[i]);
            if (command != null) {
                commands.add(command);
            }
        }

        return new BeforePositionAddedEventResult(Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN), commands);
    }

    private static ICommand createCommand(Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String commandTypeName = bundle.getString(KEY_COMMAND_TYPE);
        ICommand.Type type = Utils.safeValueOf(ICommand.Type.class, commandTypeName, null);
        if (type == null) {
            return null;
        }

        switch (type) {
            case SET_EXTRA_KEYS:
                return SetExtraKeysCommand.create(bundle.getBundle(KEY_COMMAND));
            default:
                return null;
        }
    }

    private final Result result;
    private final List<ICommand> commands;

    public BeforePositionAddedEventResult(Result result, List<ICommand> commands) {
        this.result = result;
        this.commands = commands;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        Parcelable[] commandsParcelable = new Parcelable[commands.size()];
        for (int i = 0; i < commandsParcelable.length; i++) {
            ICommand command = commands.get(i);
            Bundle commandBundle = new Bundle();
            commandBundle.putString(KEY_COMMAND_TYPE, command.getType().name());
            commandBundle.putBundle(KEY_COMMAND, command.toBundle());
            commandsParcelable[i] = commandBundle;
        }
        bundle.putParcelableArray(KEY_COMMANDS, commandsParcelable);
        return bundle;
    }


    public enum Result {
        OK,
        REJECT,
        UNKNOWN;
    }

    public interface ICommand {
        Bundle toBundle();

        Type getType();

        enum Type {
            SET_EXTRA_KEYS
        }
    }

    public static class SetExtraKeysCommand implements ICommand {
        private final static String KEY_EXTRA_KEYS = "extraKeys";

        public static SetExtraKeysCommand create(Bundle bundle) {
            Parcelable[] parcelables = bundle.getParcelableArray(KEY_EXTRA_KEYS);
            List<ExtraKey> extraKeys = new ArrayList<>(parcelables.length);
            for (int i = 0; i < parcelables.length; i++) {
                extraKeys.add(ExtraKeyMapper.from((Bundle) parcelables[i]));
            }

            return new SetExtraKeysCommand(extraKeys);
        }

        private final List<ExtraKey> extraKeys;

        public SetExtraKeysCommand(List<ExtraKey> extraKeys) {
            this.extraKeys = extraKeys;
        }

        public List<ExtraKey> getExtraKeys() {
            return extraKeys;
        }

        @Override
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            Parcelable[] parcelables = new Parcelable[extraKeys.size()];
            for (int i = 0; i < parcelables.length; i++) {
                parcelables[i] = ExtraKeyMapper.toBundle(extraKeys.get(i));
            }
            bundle.putParcelableArray(KEY_EXTRA_KEYS, parcelables);
            return bundle;
        }

        @Override
        public Type getType() {
            return Type.SET_EXTRA_KEYS;
        }
    }
}
