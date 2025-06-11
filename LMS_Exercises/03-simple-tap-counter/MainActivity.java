public class MainActivity extends AppCompatActivity {
    Button btnTap;
    TextView tvCounter;
    int tapCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTap = findViewById(R.id.btnTap);
        tvCounter = findViewById(R.id.tvCounter);

        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCount++; // Increment the tap count
                tvCounter.setText("Taps: " + tapCount); // Update the TextView
            }
        });
    }
}