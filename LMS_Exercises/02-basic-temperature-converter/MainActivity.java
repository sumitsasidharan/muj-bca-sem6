public class MainActivity extends AppCompatActivity {
    EditText etCelsius, etFahrenheit;
    Button btnToFahrenheit, btnToCelsius;
    TextView tvResultFahrenheit, tvResultCelsius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCelsius = findViewById(R.id.etCelsius);
        etFahrenheit = findViewById(R.id.etFahrenheit);
        btnToFahrenheit = findViewById(R.id.btnToFahrenheit);
        btnToCelsius = findViewById(R.id.btnToCelsius);
        tvResultFahrenheit = findViewById(R.id.tvResultFahrenheit);
        tvResultCelsius = findViewById(R.id.tvResultCelsius);

        btnToFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCelsius.getText().toString().isEmpty()) {
                    double celsius =
                        Double.parseDouble(etCelsius.getText().toString());
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    tvResultFahrenheit.setText(String.format("%.2f Fahrenheit",
                        fahrenheit));
                }
            }
        });
        
        btnToCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etFahrenheit.getText().toString().isEmpty()) {
                    double fahrenheit =
                        Double.parseDouble(etFahrenheit.getText().toString());
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    tvResultCelsius.setText(String.format("%.2f Celsius",
                        celsius));
                }
            }
        });
    }
}