
package io.github.miyazkaori.andlogger;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.miyazkaori.andlogger.databinding.ActivityMainBinding;
import io.github.miyazkaori.logger.AndLog;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // set content view to binding's root
        setContentView(binding.getRoot());
        AndLog.d("Simple debug log");
        AndLog.d("Formated debug log: %s", System.currentTimeMillis());
        
        AndLog.i("Simple info log");
        AndLog.i("Formated info log: %s", System.currentTimeMillis());
        
        AndLog.w("Simple warn log");
        AndLog.w("Formated warn log: %s", System.currentTimeMillis());
        AndLog.w(new IllegalArgumentException("IllegalArgumentException"),"Throwable warn log: %s", System.currentTimeMillis());
        
        AndLog.e("Simple error log");
        AndLog.e("Formated error log: %s", System.currentTimeMillis());
        AndLog.e(new IllegalArgumentException("IllegalArgumentException"),"Throwable error log: %s", System.currentTimeMillis());
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
