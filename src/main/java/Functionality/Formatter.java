package Functionality;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;

import java.text.DecimalFormat;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Formatter {
    private static Pattern pattern = Pattern.compile("\\d*|\\d+\\ \\d*");

    public static TextFormatter digitFormatter(){

        TextFormatter<MFXTextField> formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        return formatter;
    }

    public static TextFormatter phoneFormatter(){

        TextFormatter<String> formatter = new TextFormatter(new DefaultStringConverter(), "", new PhoneNumberFilter());

        return formatter;
    }

    public static DecimalFormat decimalFormat(){
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        return numberFormat;
    }


    private static class PhoneNumberFilter implements UnaryOperator<TextFormatter.Change> {

        @Override
        public TextFormatter.Change apply(TextFormatter.Change change) {
            if (change.isContentChange()) {
                handleBackspaceOverSpecialCharacter(change);
                if (change.getText().matches("[0-9]*")) {
                    int originalNewTextLength = change.getControlNewText().length();
                    change.setText(formatNumber(change.getControlNewText()));
                    change.setRange(0, change.getControlText().length());
                    int caretOffset = change.getControlNewText().length() - originalNewTextLength;
                    change.setCaretPosition(change.getCaretPosition() + caretOffset);
                    change.setAnchor(change.getAnchor() + caretOffset);
                    return change;
                } else {
                    return null;
                }
            }
            return change;
        }

        private void handleBackspaceOverSpecialCharacter(TextFormatter.Change change) {
            if (change.isDeleted() && (change.getSelection().getLength() == 0)) {
                if (!Character.isDigit(change.getControlText().charAt(change.getRangeStart()))) {
                    if (change.getRangeStart() > 0) {
                        change.setRange(change.getRangeStart() - 1, change.getRangeEnd() - 1);
                    }
                }
            }
        }

        private String formatNumber(String numbers) {
            numbers = numbers.replaceAll("[^\\d]", "");
            numbers = numbers.substring(0, Math.min(10, numbers.length()));
            if (numbers.length() == 0) {
                return "";
            }
            if (numbers.length() < 4) {
                return "(" + numbers;
            }
            if (numbers.length() < 7) {
                return numbers.replaceFirst("(\\d{3})(\\d+)", "($1)$2");
            }
            return numbers.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)$2-$3");
        }
    }

    public static String doublePrefix(double amount){
        String formattedAmount;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
         if (amount >= 1000000) {
            formattedAmount = decimalFormat.format(amount / 1000000) + " M";
        } else if (amount >=1000000000) {
            formattedAmount = decimalFormat.format(amount / 1000000000) + " B";
        } else {
            formattedAmount = decimalFormat.format(amount);
        }
        return formattedAmount;
    }

    public static String decimalPrefix(int amount){
        String formattedAmount;
        DecimalFormat decimalFormat = new DecimalFormat("#");
        if (amount >= 1000000) {
            formattedAmount = decimalFormat.format(amount / 1000000) + " M";
        } else if (amount >=1000000000) {
            formattedAmount = decimalFormat.format(amount / 1000000000) + " B";
        } else {
            formattedAmount = decimalFormat.format(amount);
        }
        return formattedAmount;
    }
}
