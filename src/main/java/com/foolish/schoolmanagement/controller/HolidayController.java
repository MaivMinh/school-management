package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Holiday;
import com.foolish.schoolmanagement.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/holidays")
public class HolidayController {

  private final HolidayService service;

  @Autowired
  public HolidayController(HolidayService service) {
    super();
    this.service = service;
  }

  @GetMapping("")
  public String displayHolidays(@RequestParam(required = false, defaultValue = "true") boolean festival,
                                @RequestParam(required = false, defaultValue = "true") boolean federal, Model model) {
    model.addAttribute("festival", festival);
    model.addAttribute("federal", federal);
    List<Holiday> holidays = service.findAll();
    Holiday.Type[] types = Holiday.Type.values();
    for (Holiday.Type type : types) {
      model.addAttribute(type.toString(),
              (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
    }
    model.addAttribute("username", null);
    return "holidays";
  }

}