package com.rao.aoc.day4;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassportValidator {

  public static void main(String[] args) throws FileNotFoundException {
    List<String> input = InputReader.readInputs("src/com/rao/aoc/day4/input.txt").collect(
        Collectors.toList());
    List<Passport> passports = new ArrayList<>();
    Passport currentPassport = null;
    for (String line : input) {
      if (currentPassport == null) {
        currentPassport = new Passport();
      }
      if (line.isBlank()){
        if(currentPassport.validate() && currentPassport.validate2()){
          passports.add(currentPassport);
        }
        currentPassport = null;
        continue;
      }
      for (String field : line.split(" ")) {
        switch (field.split(":")[0]) {
          case "byr": currentPassport.byr= field.split(":")[1]; break;
          case "iyr": currentPassport.iyr= field.split(":")[1]; break;
          case "eyr": currentPassport.eyr= field.split(":")[1]; break;
          case "hgt": currentPassport.hgt= field.split(":")[1]; break;
          case "hcl": currentPassport.hcl= field.split(":")[1]; break;
          case "ecl": currentPassport.ecl= field.split(":")[1]; break;
          case "pid": currentPassport.pid= field.split(":")[1]; break;
          case "cid": currentPassport.cid= field.split(":")[1]; break;
        }
      }
    }
    if(currentPassport.validate() && currentPassport.validate2()) passports.add(currentPassport);
    System.out.println(passports.size());
  }
}


class Passport {
  String byr;
  String iyr;
  String eyr;
  String hgt;
  String hcl;
  String ecl;
  String pid;
  String cid;

  public boolean validate() {
    return byr != null && iyr != null && eyr != null && hgt != null && ecl != null
           && hcl != null && pid != null;
  }
  public boolean validate2() {
    try {
    if(Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) return false;
    if(Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) return false;
    if(Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) return false;
    if (hgt.contains("cm")){
      int height = Integer.parseInt(hgt.replace("cm", ""));
      if(height < 150 || height > 193) return false;
    } else if (hgt.contains("in")) {
      int height = Integer.parseInt(hgt.replace("in", ""));
      if(height < 59 || height > 76) return false;
    } else {
      return false;
    }

      //Long.parseLong(hcl.replace("#",""), 16);
    if(!hcl.matches("#[0-9a-f]{6}")) return false;

    if(! List.of("amb","blu","brn","gry","grn","hzl","oth").contains(ecl)) return false;
    if(!pid.matches("[0-9]{9}")) return false;
    } catch (Exception e) {
      return false;
    }
    return true;
  }
public boolean val() {
  if (byr.length() != 4 || !inRange(byr, 1920, 2002)) {
    return false;
  }
  if (iyr.length() != 4 || !inRange(iyr, 2010, 2020)) {
    return false;
  }
  if (eyr.length() != 4 || !inRange(eyr, 2020,2030)) {
    return false;
  }
  if (hgt.endsWith("cm")) {
    int cm = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
    if (cm < 150 || cm > 193) {
      return false;
    }
  } else if (hgt.endsWith("in")) {
    int in = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
    if (in < 59 || in > 76) {
      return false;
    }
  } else {
    return false;
  }
  if (!hcl.matches("#[0-9a-f]{6}")) {
    return false;
  }
  if (!List.of("amb","blu","brn","gry","grn","hzl","oth").contains(ecl)) {
    return false;
  }
  if (!pid.matches("[0-9]{9}")) {
    return false;
  }
  return true;
}
  private boolean inRange(String s, int min, int max) {
    int val = Integer.parseInt(s);
    return val >= min && val <= max;
  }

}
