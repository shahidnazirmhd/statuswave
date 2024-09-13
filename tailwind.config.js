/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
      './src/main/resources/templates/**/*.html', // Thymeleaf templates
      './src/main/resources/static/**/*.js',      // Static JavaScript files (if any)
  ],
  theme: {
      extend: {},
  },
  plugins: [],
}

