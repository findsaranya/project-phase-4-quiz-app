import { environment as env } from "src/environments/environment";

export const quizEndpoints = {
    getAllQuzies : `${env.apiURL}quiz/getAllQuiz`,
    getQuizById : `${env.apiURL}quiz/getQuiz/`
}as const