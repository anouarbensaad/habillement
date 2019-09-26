FROM postgres:9.3
RUN mkdir /database
ADD ./restore/* /database
WORKDIR /database
RUN pg_restore \
    -U postgres \
    -1 "bd.backup"