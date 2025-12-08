package org.esprim.foyer.controller;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Bloc;
import org.esprim.foyer.service.BlocServiceI;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    BlocServiceI blocServiceI;
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBloc(){
        List<Bloc> blocList =blocServiceI.retrieveAllBoc();
        return blocList;
    }
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId){
        Bloc bloc =blocServiceI.retrieveBloc(blocId);
        return bloc;
    }
    @DeleteMapping("/deletebloc/{bloc-id}")
    public  void deleteBloc(@PathVariable("bloc-id") Long blocId){
        blocServiceI.deleteBloc(blocId);
    }
    @PutMapping("/modifybloc")
    public Bloc modifyBloc(@RequestBody Bloc b){
        Bloc bloc=blocServiceI.modifyBloc(b);
        return bloc;
    }
    @PostMapping("/addbloc")
    public Bloc addBloc(@RequestBody Bloc b){
        Bloc bloc=blocServiceI.addBloc(b);
        return bloc;
    }

@PostMapping("/effect-chambres-a-bloc/{blocId}")
    public Bloc affectChambresBloc(@PathVariable("blocId") Long blocId ,@RequestBody List<Long> numChambres){
        return blocServiceI.affecterChambresABloc(numChambres,blocId);
}



}
