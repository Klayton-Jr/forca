package br.com.view;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

class IntTextField extends TextField {

    private static final String REGEX_NUMBERS = "[\\d]+";
    private static final String REGEX_NO_NUMBERS = "[^\\d]";
    private static final String PREFIX_NEGATIVE = "-";
    private static final String STR_EMPTY = "";
    private boolean allowNegative;

    public IntTextField() {
        textProperty().addListener(this::textChange);
    }

    private void textChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue == null || newValue.isEmpty())
            return;

        String value = newValue.replaceAll(REGEX_NO_NUMBERS, STR_EMPTY);

        if (allowNegative && newValue.startsWith(PREFIX_NEGATIVE))
            value = PREFIX_NEGATIVE + value;

        setText(value);
    }

    public void setValue(long value) {
        setText(String.valueOf(value));
    }

    public void allowNegative(boolean allowNegative) {
        this.allowNegative = allowNegative;
    }

    public int getInt() {
        return getText().matches(REGEX_NUMBERS) ? Integer.parseInt(getText()) : 0;
    }

}
