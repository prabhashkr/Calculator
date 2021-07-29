package calculator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MathFormat {
    private String op;
    private long first;
    private long second;
    private long answer;

    public MathFormat(){
        //Jackson deserialization
    }

    public MathFormat(String op, long first, long second, long answer){
        this.op=op;
        this.first=first;
        this.second=second;
        this.answer=answer;
    }

    @JsonProperty
    public String getOp(){
        return op;
    }

    @JsonProperty
    public long getFirst(){
        return first;
    }

    @JsonProperty
    public long getSecond(){
        return second;
    }

    @JsonProperty
    public long getAnswer(){
        return answer;
    }
}
