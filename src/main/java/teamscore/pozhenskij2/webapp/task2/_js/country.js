let data = {
  Россия: ["Москва", "Санкт-Петербург", "Казань", "Самара", "Новосибирск"],
  Германия: ["Берлин", "Гамбург", "Франкфурт-на-Майне"],
  Италия: ["Рим", "Флоренция", "Венеция"],
  Франция: ["Париж", "Марсель", "Лион"],
  Япония: ["Токио", "Киото", "Осака"],
  США: ["Нью-Йорк", "Лос-Анджелес", "Чикаго"],
  Великобритания: ["Лондон", "Манчестер", "Эдинбург"],
};

let countrySelect = document.createElement("select");
let citySelect = document.createElement("select");
let sbody = document.createElement("sbody");

for (let country in data) {
  let option = document.createElement("option");
  option.textContent = country;
  countrySelect.appendChild(option);
}

countrySelect.addEventListener("change", function () {
  let selectedCountry = this.value;
  let cities = data[selectedCountry];

  citySelect.innerHTML = "";

  cities.forEach(function (element) {
    let option = document.createElement("option");
    option.textContent = element;
    citySelect.appendChild(option);
  });
});

sbody.appendChild(countrySelect)
sbody.appendChild(citySelect)
document.body.appendChild(sbody);
