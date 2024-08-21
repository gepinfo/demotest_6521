# FROM fluent/fluentd:v1.14-1

# # Use root account to use apk
# USER root

# # below RUN includes plugin as examples elasticsearch is not required
# # you may customize including plugins as you wish
# RUN apk add --no-cache --update --virtual .build-deps \
#         sudo build-base ruby-dev \
#  && sudo gem install fluent-plugin-elasticsearch \
#  && sudo gem sources --clear-all \
#  && apk del .build-deps \
#  && rm -rf /tmp/* /var/tmp/* /usr/lib/ruby/gems/*/cache/*.gem

# USER fluent

# USER fluent
FROM fluent/fluentd:v1.16.2-debian-1.0
USER root
RUN gem uninstall -I elasticsearch && gem install elasticsearch -v 8.13.0
RUN ["gem", "install", "fluent-plugin-elasticsearch", "--no-document", "--version", "5.4.3"]
USER fluent