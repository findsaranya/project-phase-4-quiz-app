export interface Quiz{
    quizId:number;
    qname: string;
}

export interface QuizResponse {
    quizId:    number;
    quizName:  string;
    questions: Question[];
}

export interface Question {
    questId:  number;
    quesName: string;
    options:  Option[];
}

export interface Option {
    id:    number;
    name:  string;
    isAns: boolean;
    isSelected : boolean
}
