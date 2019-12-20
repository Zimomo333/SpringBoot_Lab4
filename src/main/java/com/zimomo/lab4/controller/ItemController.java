package com.zimomo.lab4.controller;

import com.zimomo.lab4.entity.Item;
import com.zimomo.lab4.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
}
