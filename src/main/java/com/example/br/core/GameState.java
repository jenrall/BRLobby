package com.example.br.core;

public enum GameState {
    IDLE,        // بازی نیست
    WAITING,     // بازیکنا توی لابی منتظرن
    COUNTDOWN,   // شمارش معکوس
    IN_GAME,     // بازی در حال اجرا
    ENDING       // پایان بازی
}
