package com.example.demo.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.ptr.IntByReference;

public class WindowsUtils {
    private static final int MAX_TITLE_LENGTH = 1024;

    public static String getActiveApplication() {
        char[] buffer = new char[MAX_TITLE_LENGTH];
        User32.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        if (hwnd == null) return "Unknown";

        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
        String windowTitle = Native.toString(buffer);
        return windowTitle.isEmpty() ? "Unknown" : windowTitle;
    }
}
