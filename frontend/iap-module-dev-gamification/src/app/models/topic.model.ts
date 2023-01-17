import { Quest } from "./quest.model";

export interface TopicOverview {
  [topic: string]: Quest[];
}
