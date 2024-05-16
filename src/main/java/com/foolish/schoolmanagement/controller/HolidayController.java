package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/holidays")
public class HolidayController {

  @GetMapping("")
  public String displayHolidays(@RequestParam(required = false, defaultValue = "true") boolean festival,
                                @RequestParam(required = false, defaultValue = "true") boolean federal, Model model) {
    model.addAttribute("festival", festival);
    model.addAttribute("federal", federal);
    List<Holiday> holidays = Arrays.asList(
            new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
            new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
            new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
            new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
            new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
            new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
            new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
            new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
    );
    Holiday.Type[] types = Holiday.Type.values();
    for (Holiday.Type type : types) {
      model.addAttribute(type.toString(),
              (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
    }
    model.addAttribute("username", null);
    return "holidays";
  }

  @GetMapping(value = {"/{display}"})
  public String displayHolidaysPage(@PathVariable String display, Model model) {
    model.addAttribute("festival", display.equals("all") || display.equals("festival"));
    model.addAttribute("federal", display.equals("all") || display.equals("federal"));
    List<Holiday> holidays = Arrays.asList(
            new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
            new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
            new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
            new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
            new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
            new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
            new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
            new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
    );
    Holiday.Type[] types = Holiday.Type.values();
    for (Holiday.Type type : types) {
      model.addAttribute(type.toString(),
              (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
    }
    model.addAttribute("username", null);
    return "holidays";
  }
}