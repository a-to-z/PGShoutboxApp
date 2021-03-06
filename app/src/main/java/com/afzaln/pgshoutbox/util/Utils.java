package com.afzaln.pgshoutbox.util;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import timber.log.Timber;

/**
 * Created by afzal on 2016-11-20.
 */

public class Utils {
    public static class HashUtils {
        public static String md5(final String string) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.reset();
                digest.update(string.getBytes());
                byte[] a = digest.digest();
                int len = a.length;
                StringBuilder sb = new StringBuilder(len << 1);
                for (int i = 0; i < len; i++) {
                    sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                    sb.append(Character.forDigit(a[i] & 0x0f, 16));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                Timber.e(e, "Couldn't generate MD5 hash");
            }
            return "";
        }
    }

    public static class ValidationUtils {
        public static boolean validate(boolean... valids) {
            for (boolean valid : valids) {
                if (!valid) {
                    return false;
                }
            }
            return true;
        }

        public static int checkNumber(TextInputEditText editText) throws NumberFormatException {
            int input = Integer.parseInt(editText.getText().toString());
            return input;
        }

        public static boolean check(boolean condition, TextInputLayout layout, String errorMsg) {
            if (condition) {
                layout.setError(errorMsg);
                return false;
            }

            layout.setError(null);
            return true;
        }
    }
}
