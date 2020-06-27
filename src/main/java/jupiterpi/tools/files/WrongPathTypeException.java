package jupiterpi.tools.files;

public class WrongPathTypeException extends Exception {
    enum PathType {
        DIR, FILE
    }

    PathType currentType;
    PathType rightType;

    public WrongPathTypeException(PathType currentType) {
        this.currentType = currentType;
        switch (currentType) {
            case DIR: rightType = PathType.FILE; break;
            default: rightType = PathType.DIR;
        }
    }

    public PathType getCurrentType() {
        return currentType;
    }

    public PathType getRightType() {
        return rightType;
    }
}