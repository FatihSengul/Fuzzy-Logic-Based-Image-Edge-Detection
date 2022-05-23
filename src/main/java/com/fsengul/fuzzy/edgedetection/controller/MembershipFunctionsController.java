package com.fsengul.fuzzy.edgedetection.controller;

import com.fsengul.fuzzy.edgedetection.service.FuzzyLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MembershipFunctionsController {

    @Autowired
    private FuzzyLogicService fuzzyLogicService;

    @RequestMapping(value = "/change_black_white_mf", method = RequestMethod.POST)
    public ResponseEntity changeBlackWhiteMf(
            @RequestParam("black_start") Integer blackStart,
            @RequestParam("black_end") Integer blackEnd,
            @RequestParam("white_start") Integer whiteStart,
            @RequestParam("white_end") Integer whiteEnd) {
        fuzzyLogicService.modifyBlackWhiteMembershipFunctions(blackStart, blackEnd,
                whiteStart, whiteEnd);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/change_edge_mf", method = RequestMethod.POST)
    public ResponseEntity changeEdgeMf(
            @RequestParam("edge_end") Integer edgeEnd) {
        fuzzyLogicService.modifyEdgeMembershipFunction(edgeEnd);
        return new ResponseEntity(HttpStatus.OK);
    }
}
