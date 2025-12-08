package org.esprim.foyer.service;

import org.esprim.foyer.entity.Universite;

import java.util.List;

public interface UniversiteServiceI {
   public List<Universite>  getAllUniversiter();
   public  Universite getUniversiteById(long id);
   public  void  deleteUniversiteById(long iduniversite);
   public  Universite addUniversite(Universite universite);
   public  Universite modifyUniversity(Universite universite);
    public Universite affecterUniversite(Long foyerId, String nomuniversite);
    public Universite desaffecterUniversite(Long iduniversite);
}
