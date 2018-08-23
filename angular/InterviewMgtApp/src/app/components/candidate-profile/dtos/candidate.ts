import { Experience } from "./experience";
import { Skill } from "./skill";

export interface Candidate {

  name : string,
  address : string,
  email : string,
  availability : boolean,
  cover : string,
  score : number,
  phones : number[],
  status : string,
  experiences : Experience[],
  skills : Skill[]
}
