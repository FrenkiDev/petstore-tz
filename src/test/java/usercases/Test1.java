package usercases;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Зоомагазин->Домашний питомец->Пользовательские сценарии")
public class Test1 {

  @Test
  @DisplayName("Купить Собаку в Магазине")
  void should_BuyDogInTheStore() {
    Allure.step("Авторизоваться");
    Allure.step("Найти собаку", () -> {
      Allure.step("Найти домашнее животное по статусу");
      Allure.step("Найти собаку из списка доступных для продажи по статусу");
    });
    Allure.step("Купить собаку", () -> {
      Allure.step("Оформить заказ на домашнее животное");
      Allure.step("Найти заказ на покупку по идентификатору");
      Allure.step("Удалить заказ на покупку по идентификатору");
    });
    Allure.step("Собака куплена и едет домой", () -> {
      Allure.step("Обновить существующего питомца");
      Allure.step("Удаляет питомца");
    });
    Allure.step("Выйти");
  }
}
