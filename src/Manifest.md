# Манифест

## Версиии использованного програмного обеспечения

```jsx
Operating System: Linux Mint 21                   
Kernel: Linux 5.15.0-47-generic
java version "18.0.2.1" 2022-08-18
Java HotSpot(TM) 64-Bit Server VM (build 18.0.2.1+1-1, mixed mode, sharing)
javac 18.0.2.1
```

## Компиляция

В директории **src** содержатся 3 файла с расширением **.java**

Компиляция производится в **терминале** командой

```jsx
javac MergeArrays.java BinaryFileBuffer.java FilesProcessing.java
```

## Исполнение

Исполнение програмы производиться в терминале командой 

```jsx
java MergeArrays sortmode(optinal) type outfile infiles
```

### Агрменты командной строки

- **sortmode** : режим сортировки (-a или -d), необязательный, по умолчанию сортируется по возрастанию.
- **type** : тип данных (-s или -i), обязательный.
- **outfile** : имя выходного файла, обязательное.
- **infiles** : имена входных файлов, не менее одного.

Файлы должны содержатся в директории **src**